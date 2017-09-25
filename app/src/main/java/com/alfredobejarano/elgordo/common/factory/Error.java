package com.alfredobejarano.elgordo.common.factory;

import com.alfredobejarano.elgordo.R;

import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_BAD_METHOD;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CLIENT_TIMEOUT;
import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_ENTITY_TOO_LARGE;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
import static java.net.HttpURLConnection.HTTP_GONE;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_LENGTH_REQUIRED;
import static java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_NOT_IMPLEMENTED;
import static java.net.HttpURLConnection.HTTP_PAYMENT_REQUIRED;
import static java.net.HttpURLConnection.HTTP_PRECON_FAILED;
import static java.net.HttpURLConnection.HTTP_PROXY_AUTH;
import static java.net.HttpURLConnection.HTTP_REQ_TOO_LONG;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;
import static java.net.HttpURLConnection.HTTP_UNSUPPORTED_TYPE;
import static java.net.HttpURLConnection.HTTP_VERSION;

/**
 * This class handles error responses
 * from the server.
 *
 * @author @AlfredoBejarano
 * @version 1.0
 * @since 24/09/2017
 */

public class Error {
    public static int Factory(int errorCode) {
        if (errorCode == HTTP_BAD_REQUEST) {
            return R.string.error_bad_request;
        } else if (errorCode == HTTP_UNAUTHORIZED) {
            return R.string.error_unauthorized;
        } else if (errorCode == HTTP_PAYMENT_REQUIRED) {
            return R.string.error_payment_required;
        } else if (errorCode == HTTP_FORBIDDEN) {
            return R.string.error_forbidden;
        } else if (errorCode == HTTP_NOT_FOUND) {
            return R.string.error_not_found;
        } else if (errorCode == HTTP_BAD_METHOD) {
            return R.string.error_method_not_allowed;
        } else if (errorCode == HTTP_NOT_ACCEPTABLE) {
            return R.string.error_not_acceptable;
        } else if (errorCode == HTTP_PROXY_AUTH) {
            return R.string.error_proxy_authentication;
        } else if (errorCode == HTTP_CLIENT_TIMEOUT) {
            return R.string.error_request_timeout;
        } else if (errorCode == HTTP_CONFLICT) {
            return R.string.error_conflict;
        } else if (errorCode == HTTP_GONE) {
            return R.string.error_gone;
        } else if (errorCode == HTTP_LENGTH_REQUIRED) {
            return R.string.error_length_required;
        } else if (errorCode == HTTP_PRECON_FAILED) {
            return R.string.error_precondition_failed;
        } else if (errorCode == HTTP_ENTITY_TOO_LARGE) {
            return R.string.error_payload_too_large;
        } else if (errorCode == HTTP_REQ_TOO_LONG) {
            return R.string.error_uri_too_long;
        } else if (errorCode == HTTP_UNSUPPORTED_TYPE) {
            return R.string.error_unsupported_media_type;
        } else if (errorCode == HTTP_INTERNAL_ERROR) {
            return R.string.error_internal_error;
        } else if (errorCode == HTTP_NOT_IMPLEMENTED) {
            return R.string.error_not_implemented;
        } else if (errorCode == HTTP_BAD_GATEWAY) {
            return R.string.error_bad_gateway;
        } else if (errorCode == HTTP_UNAVAILABLE) {
            return R.string.error_service_unavailable;
        } else if (errorCode == HTTP_GATEWAY_TIMEOUT) {
            return R.string.error_gateway_timeout;
        } else if (errorCode == HTTP_VERSION) {
            return R.string.error_http_version_not_supported;
        } else {
            return R.string.error_internal_error;
        }
    }
}
