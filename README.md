# Google reCAPTCHA (v2.0) for Mendix

Protect your Mendix app from spam and abuse while letting real people pass through with ease.

## Contributing

For more information on contributing to this repository visit [Contributing to a GitHub repository](https://world.mendix.com/display/howto50/Contributing+to+a+GitHub+repository)!

## Typical usage scenario

If you want Google reCAPTCHA to be used in your public form pages to prevent bot messages.

## Dependencies
To provide server side validation of the response token we use the [Rest Services](https://appstore.home.mendix.com/link/app/997/Mendix/Rest-Services) module from the Mendix appstore.

## Description

This Google reCAPTCHA funcitonality for Mendix consists out of two components.
You have the custom Mendix widget to render the Google reCAPTCHA (v2.0) widget within the Mendix front-end.
And there is a module that contains the server side validation of the Google reCAPTCHA response token.

A test project is included to have an example on how to implement the Google reCAPTCHA functionality.
The credentials are:
- Username = MxAdmin
- Password = 1

Out of the box the Google test keys are used to test the functionality. It is advised to create specific site keys [at Google](https://www.google.com/recaptcha/admin#createsite) and enter them into the provided module settings page/snippet as soon as possible.

## Installation Steps
1. Download the module with the widget included from the Mendix appstore.
2. Include the provided after startup microflow "ASU_reCAPTCHA2" in your application startup.
2. Assign the provided settings snippet to a settings page and add it to the navigation of your app. (Administrator)
3. Create/use the site keys [at Google](https://www.google.com/recaptcha/admin#createsite) and include them in Mendix app via the settings page/snippet.
4. Include the reCAPTCHA2 widget on a public page with form/dataview.
5. Configure the reCAPTCHA2 widget to write the response token to an attribute of the context entity.
5. Configure the reCAPTCHA2 widget to read the site key via the provided "GET_Sitekey" microflow.
6. Model a microflow to handle the submit/save action.
7. Use the provided validation microflow "SUB_GoogleRecaptcha_Siteverify" to verify the provided response token of the entity and take proper actions.
