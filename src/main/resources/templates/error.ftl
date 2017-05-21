<#import "./fragments/page.master.ftl" as layout/>

<@layout.indexmaster titleKey="page.title.error">
<form class="form-signin" action="/login" method="post" modelAttribute="UserForm">
    <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.error')}</h2>
    <p>print nice error</p>
</form>
</@layout.indexmaster>