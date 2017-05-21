<#import "./fragments/page.master.ftl" as layout/>

<@layout.indexmaster titleKey="page.title.home">
<div class="page-header">
    <h1>${springMacroRequestContext.getMessage('page.header.welcome')}</h1>
</div>
</@layout.indexmaster>