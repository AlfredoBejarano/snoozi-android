package com.alfredobejarano.elgordo.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by @AlfredoBejarano on 29/06/2017.
 * <p>
 * This class will handle string and text validations.
 */

public class TextUtils {

    private TextUtils() {
        // Hides this utility class constructor.
    }

    /**
     * This method returns a text from an HTML source.
     *
     * @param source String - The text with HTML formatting.
     * @return Spanned - The text with the format.
     */
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    /**
     * This method returns a text with a HTML format retrieved from the string
     * resources.
     *
     * @param resId   int The resource Id of the String.
     * @param context Context for retrieving the String value.
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(@StringRes int resId, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(context.getString(resId), FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(context.getString(resId));
        }
    }

    /**
     * Returns a valid String, this method will come handy when retrieving strings
     * from html sources.
     *
     * @param text The text to be validated.
     * @return String - The String in a valid format.
     */
    public static String safeString(Spanned text) {
        return text == null ? "" : String.valueOf(text);
    }

    /**
     * Returns a valid String, this method will come handy when retrieving strings
     * from widgets.
     *
     * @param text The text to be validated.
     * @return String - The String in a valid format.
     */
    public static String safeString(Spannable text) {
        return text == null ? "" : String.valueOf(text);
    }

    /**
     * Returns a valid String, this method will come handy when retrieving strings
     * from the backend or other sources.
     *
     * @param text The text to be validated.
     * @return String - The String in a valid format.
     */
    public static String safeString(String text) {
        return text == null ? "" : String.valueOf(text);
    }

    /**
     * Returns a valid String, this method will come handy when retrieving strings
     * from html res sources.
     *
     * @param text The text to be validated.
     * @return String - The String in a valid format.
     */
    public static String safeString(CharSequence text) {
        return text == null ? "" : String.valueOf(text);
    }

    /**
     * Converts an integer into a String with a 0 in front of it if the value
     * is less than 9.
     *
     * @param number - The number
     * @return String with the number properly formatted.
     */
    public static String toFormattedString(int number) {
        return number > 9 ? String.valueOf(number) : "0" + number;
    }

    /**
     * Returns safely a String value from the Intent extras.
     *
     * @param name The key name of the extra.
     * @return The extra value.
     */
    public static String retrieveStringArgument(String name, Intent intent) {
        return TextUtils.safeString(intent.getStringExtra(name));
    }
}
