// Copyright Vespa.ai. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.

package ai.vespa.example.shopping.site.view;

import java.util.Map;
import java.util.function.Consumer;

public class SimpleHtmlBuilder {

    private final StringBuilder sb;

    public SimpleHtmlBuilder() {
        sb = new StringBuilder();
    }

    public void element(String elem, Map<String, String> attrs) {
        element(elem, attrs, null);
    }

    public void element(String elem, Map<String, String> attrs, Consumer<SimpleHtmlBuilder> inner) {
        sb.append("<").append(elem).append(" ");
        if (attrs != null) {
            attrs.forEach((k,v) -> sb.append(k).append("=\"").append(v).append("\" "));
        }
        sb.append(">\n");
        if (inner != null) {
            inner.accept(this);
        }
        sb.append("</").append(elem).append(">\n");
    }

    public void div(String cls, Consumer<SimpleHtmlBuilder> inner) {
        element("div", Map.of("class", cls), inner);
    }

    public void a(String href, Consumer<SimpleHtmlBuilder> inner) {
        element("a", Map.of("href", href), inner);
    }

    public void img(String src) {
        element("img", Map.of("src", src), null);
    }

    public void span(String cls, Consumer<SimpleHtmlBuilder> inner) {
        element("span", Map.of("class", cls), inner);
    }

    public void text(String text) {
        sb.append(text);
    }

    public String build() {
        return sb.toString();
    }

    public static String ratingToStarsImage(int stars, int count) {
        return ratingToStarsImage((float)stars / (float)count);
    }

    public static String ratingToStarsEmoji(int stars, int count) {
        return ratingToStarsEmoji((float)stars / (float)count);
    }

    public static String ratingToStarsImage(float rating) {
        final String dir = "/site/img/";
        if (rating >= 4.75) return dir + "vespas_5_0.png";
        if (rating >= 3.75) return dir + "vespas_4_0.png";
        if (rating >= 2.75) return dir + "vespas_3_0.png";
        if (rating >= 1.75) return dir + "vespas_2_0.png";
        if (rating >= 0.75) return dir + "vespas_1_0.png";
        return dir + "vespas_0_0.png";
    }

    public static String ratingToStarsEmoji(float rating) {

        if (rating >= 4.75) return  "⭐⭐⭐⭐⭐";
        if (rating >= 3.75) return  "⭐⭐⭐⭐";
        if (rating >= 2.75) return  "⭐⭐⭐";
        if (rating >= 1.75) return  "⭐⭐";
        if (rating >= 0.75) return  "⭐";
        return "⭐";
    }

    public static String truncate(String str, int max) {
        if (str.length() < max)
            return str;
        int lastSpace = str.lastIndexOf(" ", max - 4);
        return str.substring(0, lastSpace < 0 ? max - 4 : lastSpace) + " ...";
    }

}
