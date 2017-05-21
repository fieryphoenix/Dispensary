<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<#macro page_head>
<link href="<@spring.url '/css/login.css'/>" rel="stylesheet">
</#macro>

<#assign layoutOut>

<form class="form-signin" action="/login-processing" method="post" modelAttribute="UserForm">
    <@spring.bind "UserForm"/>

    <#if spring.status.error>
        <div class="alert alert-danger" role="alert">
            <#list spring.status.errorMessages as error>
                <strong>${springMacroRequestContext.getMessage('page.alert.error')}</strong> ${springMacroRequestContext.getMessage(error)}
                <br/>
            </#list>
        </div>
    </#if>

    <div class="page-header">
        <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.signin')}</h2>
    </div>

    <input type="hidden" id="id" value=""/>
    <label for="username"
           class="sr-only">${springMacroRequestContext.getMessage('page.field.username')}</label>
    <@spring.formInput "UserForm.username" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.username')}' required autofocus" "text"/><@spring.showErrors "<br>"/>
    <label for="password"
           class="sr-only">${springMacroRequestContext.getMessage('page.field.password')}</label>
    <@spring.formInput "UserForm.password" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.password')}' required" "password"/><@spring.showErrors "<br>"/>
    <div class="checkbox">
        <label>
        <#--<@spring.formCheckbox "Remember"/>${springMacroRequestContext.getMessage('page.field.rememberme')}-->
        </label>
    </div>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button class="btn btn-lg btn-primary btn-block"
            type="submit">${springMacroRequestContext.getMessage('page.button.signin')}</button>
</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.login">
</@layout.indexmaster>