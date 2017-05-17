<#import "./fragments/page.master.ftl" as layout/>

<@layout.indexmaster titleKey="page.title.home">
<h1>${springMacroRequestContext.getMessage('page.header.welcome')}</h1>
</@layout.indexmaster>