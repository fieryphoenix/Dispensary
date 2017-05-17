<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<#macro page_head>
<link href="<@spring.url '/css/login.css'/>" rel="stylesheet">
</#macro>

<@layout.indexmaster titleKey="page.title.login">

<form class="form-signin" action="/login" method="post" modelAttribute="userForm">
    <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.signin')}</h2>
    <label for="login" class="sr-only">${springMacroRequestContext.getMessage('page.field.username')}</label>
    <input type="text" id="login" class="form-control"
           placeholder="${springMacroRequestContext.getMessage('page.field.username')}" required autofocus>
    <label for="inputPassword" class="sr-only">${springMacroRequestContext.getMessage('page.field.password')}</label>
    <input type="password" id="inputPassword" class="form-control"
           placeholder="${springMacroRequestContext.getMessage('page.field.password')}" required>
    <div class="checkbox">
        <label>
            <input type="checkbox" value="remember-me"> ${springMacroRequestContext.getMessage('page.field.rememberme')}
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block"
            type="submit">${springMacroRequestContext.getMessage('page.button.signin')}</button>
</form>

</@layout.indexmaster>