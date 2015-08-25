/*jslint white:true, nomen: true, plusplus: true, vars:true */
/*jshint browser:true */
/*global mx, define, require, browser, devel, console, document, grecaptcha */
/*mendix */
/*
    reCAPTCHA2
    ========================

    @file      : reCAPTCHA2.js
    @version   : 1.0
    @author    : Marcus Groen
    @date      : Fri, 14 Aug 2015 11:32:27 GMT
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
    'reCAPTCHA/lib/jquery-1.11.2', 'dojo/text!reCAPTCHA/widget/template/reCAPTCHA2.html'
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, domQuery, domProp,
              domGeom, domClass, domStyle, domConstruct, dojoArray, lang, text, html, event,
              _jQuery, widgetTemplate) {
    'use strict';

    var $ = _jQuery.noConflict(true);
    
    // Declare widget's prototype.
    return declare('reCAPTCHA.widget.reCAPTCHA2', [_WidgetBase, _TemplatedMixin], {

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
            if (typeof window._grecaptchaWidgets === "undefined") {
                window._grecaptcha_widgets = [];
            }
        },

        // dijit._WidgetBase.postCreate is called after constructing the widget. Implement to do extra setup work.
        postCreate: function () {
            
            // create recaptcha placeholder
            this._recaptchaNode = domConstruct.create("div", {
                "id" : this.id + "-recaptcha"
            });
            domConstruct.place(this._recaptchaNode, this.id);

            // recaptcha api script
            if (window.__google_recaptcha_client !== true && $("#google_recaptcha_script").length === 0) {
                try {
                    this._googleRecaptchaApiScript = domConstruct.create("script", {"src" : ("https:" === document.location.protocol ? "https" : "http") + "://www.google.com/recaptcha/api.js?render=explicit",  "id":"google_recaptcha_script", "async":"true", "defer":"true"});
                    domConstruct.place(this._googleRecaptchaApiScript, domQuery("head")[0]);
                } catch(e) {
                    console.error("Failed to include Google Recaptcha script tag: " + e.message);
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
                        applyto: 'selection',
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
                        console.log(this.id + ': An error occurred while executing microflow: ' + error.description);
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
            if (window.__google_recaptcha_client === true) {
                grecaptcha.reset(this._widgetId);
                window._grecaptcha_widgets.splice(dojoArray.indexOf(this._widgetId),1);
            }
        },

        // render recaptcha widget when library is loaded
        _grecaptchaRender: function () {
            // define recaptcha render options
            this._renderOptions = $.extend({},{
                "sitekey": (this._siteKey !== null) ? this._siteKey : "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI",
                "theme": (this.themeString !== "") ? this.themeString : undefined,
                "type": (this.typeString !== "") ? this.typeString : undefined,
                "size": (this.sizeString !== "") ? this.sizeString : undefined,
                "tabindex": (this.tabIndexInteger > 0 ) ? this.tabIndexInteger : 0,
                "callback": lang.hitch(this,function(response){
                    // store response token in entity for server side validation
                    this._contextObj.set(this.responseTokenAttribute, response);
                })
            });
            // render widget
            this._startTime = new Date().getTime();
            if (typeof grecaptcha !== 'undefined') {
                try {
                    this._widgetId = grecaptcha.render(this.id + "-recaptcha", this._renderOptions);
                    window._grecaptcha_widgets.push(this._widgetId);
                } catch(e) {
                    console.error("Failed to render recaptcha widget: " + e.message);
                }
            } else {
                var duration =  new Date().getTime() - this._startTime;
                if (duration > 15000) {
                    console.warn("Recaptcha widget " + this.id + " timeout, grecaptcha is undefined.");
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
            /*if (this._contextObj) {

                _objectHandle = this.subscribe({
                    guid: this._contextObj.getGuid(),
                    callback: lang.hitch(this, function (guid) {
                        try {
                            grecaptcha.reset(this._widgetId);
                        } catch(e) {
                            console.error("Failed to reset recaptcha widget: " + e.message);
                        }
                    })
                });

                _attrHandle = this.subscribe({
                    guid: this._contextObj.getGuid(),
                    attr: this.responseTokenAttribute,
                    callback: lang.hitch(this, function (guid, attr, attrValue) {
                        try {
                            grecaptcha.reset(this._widgetId);
                        } catch(e) {
                            console.error("Failed to reset recaptcha widget: " + e.message);
                        }
                    })
                });

                _validationHandle = this.subscribe({
                    guid: this._contextObj.getGuid(),
                    val: true,
                    callback: lang.hitch(this, this._handleValidation)
                });

                this._handles = [_objectHandle, _attrHandle, _validationHandle];
            }*/
        }
    });
});
require(['reCAPTCHA/widget/reCAPTCHA2'], function () {
    'use strict';
});