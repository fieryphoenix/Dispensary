<#import "./fragments/page.master.ftl" as layout/>

<@layout.indexmaster titleKey="page.title.error">
<div class="page-header">
    <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.error')}</h2>
    <p>print nice error</p>
</div>
</@layout.indexmaster>