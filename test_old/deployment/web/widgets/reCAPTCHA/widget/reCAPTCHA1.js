/*jslint white:true, nomen: true, plusplus: true, vars:true */
/*jshint browser:true */
/*global mx, define, require, browser, devel, console, document, Recaptcha, RecaptchaState, mendix */
/*
    reCAPTCHA2
    ========================

    @file      : reCAPTCHA1.js
    @version   : 1.0
    @author    : Marcus Groen
    @date      : Fri, 23 Oct 2015 13:00:00 GMT
    @copyright : Incentro
    @license   : Apache 2.0

    Documentation
    ========================
    Protect your Mendix app from spam and abuse while letting real people pass through with ease.
*/

// Required module list. Remove unnecessary modules, you can always get them back from the boilerplate.
define([
    'dojo/_base/declare', 'mxui/widget/_WidgetBase', 'dijit/_TemplatedMixin',
    'mxui/dom', 'dojo/dom', 'dojo/query', 'dojo/dom-prop', 'dojo/dom-geometry',
    'dojo/dom-class', 'dojo/dom-style', 'dojo/dom-construct', 'dojo/_base/array',
    'dojo/_base/lang', 'dojo/text', 'dojo/html', 'dojo/_base/event',
    'reCAPTCHA/lib/jquery-1.11.2', 'dojo/text!reCAPTCHA/widget/template/reCAPTCHA1.html'
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, domQuery, domProp,
              domGeom, domClass, domStyle, domConstruct, dojoArray, lang, text, html, event,
              _jQuery, widgetTemplate) {
    'use strict';

    var $ = _jQuery.noConflict(true);

    // Declare widget's prototype.
    return declare('reCAPTCHA.widget.reCAPTCHA1', [_WidgetBase, _TemplatedMixin], {

        // _TemplatedMixin will create our dom node using this HTML template.
        templateString: widgetTemplate,

        // Internal variables. Non-primitives created in the prototype are shared between all widget instances.
        _handles: null,
        _contextObj: null,
        _alertDiv: null,
        _recaptchaNode: null,
        _widgetId: null,
        _renderOptions: null,
        _siteKey: null,

        // dojo.declare.constructor is called to construct the widget instance. Implement to initialize non-primitive properties.
        constructor: function () {
            this._handles = [];
            if (typeof window._grecaptcha1_widgets === "undefined") {
                window._grecaptcha1_widgets = [];
            }
        },

        // dijit._WidgetBase.postCreate is called after constructing the widget. Implement to do extra setup work.
        postCreate: function () {

            // create recaptcha placeholder
            /*this._recaptchaNode = domConstruct.create("div", {
                "id" : this.id + "-recaptcha"
            });
            domConstruct.place(this._recaptchaNode, this.id);*/

            // recaptcha api script
            if (typeof window.Recaptcha === "undefined" && $("#google_recaptcha1_script").length === 0) {
                try {
                    this._googleRecaptchaApiScript = domConstruct.create("script", {"src" : ("https:" === document.location.protocol ? "https" : "http") + "://www.google.com/recaptcha/api/js/recaptcha_ajax.js",  "id":"google_recaptcha1_script", "async":"true", "defer":"true"});
                    domConstruct.place(this._googleRecaptchaApiScript, domQuery("head")[0]);
                } catch(e) {
                    console.error(this.id + ": Failed to include Google Recaptcha script tag: " + e.message);
                }
            }

        },

        // mxui.widget._WidgetBase.update is called when context is changed or initialized. Implement to re-render and / or fetch data.
        update: function (object, callback) {

            this._contextObj = object;

            // get sitekey from microflow
            if (this.mfGetSiteKey !== "" && this.siteKeyAttribute !== "") {
                mx.data.action({
                    params: {
                        applyto: "selection",
                        actionname: this.mfGetSiteKey,
                        guids: [this._contextObj.getGuid()]
                    },
                    callback: lang.hitch(this,function(obj){
                        if (obj) {
                            this._siteKey = obj[0].jsonData.attributes[this.siteKeyAttribute].value;
                        }
                        setTimeout(lang.hitch(this,this._grecaptchaRender),100);
                    }),
                    error: lang.hitch(this,function(error){
                        console.error(this.id + ": An error occurred while executing microflow: " + error.description);
                    })
                }, this);
            } else {
                setTimeout(lang.hitch(this,this._grecaptchaRender),100);
            }

            this._resetSubscriptions();

            callback();

        },

        // mxui.widget._WidgetBase.enable is called when the widget should enable editing. Implement to enable editing if widget is input widget.
        enable: function () {},

        // mxui.widget._WidgetBase.enable is called when the widget should disable editing. Implement to disable editing if widget is input widget.
        disable: function () {},

        // mxui.widget._WidgetBase.resize is called when the page's layout is recalculated. Implement to do sizing calculations. Prefer using CSS instead.
        resize: function (box) {},

        // mxui.widget._WidgetBase.uninitialize is called when the widget is destroyed. Implement to do special tear-down work.
        uninitialize: function () {
            // Clean up listeners, helper objects, etc. There is no need to remove listeners added with this.connect / this.subscribe / this.own.
            window._grecaptcha1_widgets.splice(dojoArray.indexOf(this.id),1);
        },

        // render recaptcha widget when library is loaded
        _grecaptchaRender: function () {

            this._startTime = new Date().getTime();
            if (typeof window.Recaptcha !== "undefined" && typeof window.Recaptcha.finish_reload !== "undefined") {

                // Clone existing recaptcha, if already there.
                if (window.Recaptcha.widget !== null) {
                   // setup synced reload for multiple recaptchas
                    try {
                        if (typeof window.reloadMendixRecaptcha === "undefined") {
                            window.reloadMendixRecaptcha = function (challenge) {
                                $(":input[name=recaptcha_response_field]").val("");
                                $(":input[name=recaptcha_challenge_field]").val(challenge);
                                $(".recaptcha_image").attr("src", ("https:" === document.location.protocol ? "https" : "http") + "://www.google.com/recaptcha/api/image?c=" + challenge);
                            };
                            window.Recaptcha.finish_reload_original = window.Recaptcha.finish_reload;
                            window.Recaptcha.finish_reload = function (challenge, b, c) {
                                window.Recaptcha.finish_reload_original(challenge, b, c);
                                window.reloadMendixRecaptcha(challenge);
                            };
                            window.Recaptcha.challenge_callback_original = window.Recaptcha.challenge_callback;
                            window.Recaptcha.challenge_callback = function () {
                                window.Recaptcha.challenge_callback_original();
                                window.reloadMendixRecaptcha(window.RecaptchaState.challenge);
                            };
                        }
                    } catch(setupSyncError) {
                        console.error(this.id + ": Failed to setup synced reload: " + setupSyncError.message);
                    }
                    // clone
                    try {
                        setTimeout(lang.hitch(this,function(){
                            var clone = $(window.Recaptcha.widget).clone(true,true);
                            $("#" + this.id).html(clone);
                        }),500);
                    } catch(e) {
                        console.error(this.id + ": Failed to clone recaptcha widget: " + e.message);
                    }
                } else {
                    // create new recaptcha
                    try {
                        this._renderOptions = $.extend({},{
                            "theme": (this.themeString !== "") ? this.themeString : undefined,
                            "lang": (this.langString !== "") ? this.langString : undefined,
                            "tabindex": (this.tabIndexInteger > 0 ) ? this.tabIndexInteger : 0
                        });
                        if ($(".btn.mx-button.mx-name-" + this.btnName).length !== 0) {
                            $(".btn.mx-button.mx-name-" + this.btnName).mousedown(lang.hitch(this,function(e){
                                var challenge = $("#" + this.id + " :input[name=recaptcha_challenge_field]").val();
                                this._contextObj.set(this.challengeAttribute, challenge);
                                var response = $("#" + this.id + " :input[name=recaptcha_response_field]").val();
                                this._contextObj.set(this.responseTokenAttribute, response);
                            }));
                            var siteKey = (this._siteKey !== null) ? this._siteKey : "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI";
                            window.Recaptcha.create(siteKey);
                            window._grecaptcha1_widgets.push(this.id);
                        } else {
                            console.error(this.id + ": Failed to render recaptcha widget: Mendix action button not found.");
                        }
                    } catch(newRecaptchaError) {
                        console.error(this.id + ": Failed to render recaptcha widget: " + newRecaptchaError.message);
                    }
                }

            } else {
                var duration =  new Date().getTime() - this._startTime;
                if (duration > 15000) {
                    console.error(this.id + ": Recaptcha widget timeout, Recaptcha API is not loaded.");
                    return;
                }
                setTimeout(lang.hitch(this,this._grecaptchaRender),250);
            }

        },

        // We want to stop events on a mobile device
        _stopBubblingEventOnMobile: function (e) {
            if (typeof document.ontouchstart !== 'undefined') {
                event.stop(e);
            }
        },

        // Reset subscriptions.
        _resetSubscriptions: function () {
            var _objectHandle = null,
                _attrHandle = null,
                _validationHandle = null;

            // Release handles on previous object, if any.
            if (this._handles) {
                this._handles.forEach(function (handle, i) {
                    mx.data.unsubscribe(handle);
                });
                this._handles = [];
            }

            // When a mendix object exists create subscribtions.
            if (this._contextObj) {

                _objectHandle = this.subscribe({
                    guid: this._contextObj.getGuid(),
                    callback: lang.hitch(this,function(guid){
                        try {
                            if (typeof window.Recaptcha !== 'undefined') {
                                if (typeof window.RecaptchaState !== 'undefined') {
                                    window.Recaptcha.reload();
                                }
                            }
                        } catch(e) {
                            console.error(this.id + ": Failed to reload recaptcha widget: " + e.message);
                        }
                    })
                });

                this._handles = [_objectHandle];
            }
        }
    });
});
require(['reCAPTCHA/widget/reCAPTCHA1'], function () {
    'use strict';
});
