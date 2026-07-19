package com.demo_qa.ui.helper;

import com.codeborne.selenide.Selenide;

public class JavaScriptHelper {

    public JavaScriptHelper removeFixedElements(){
        Selenide.executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);
        return this;
    }
}
