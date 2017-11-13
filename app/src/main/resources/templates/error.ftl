<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<@layout.indexmaster titleKey="page.title.error">
<div class="page-header">

    <h2>${springMacroRequestContext.getMessage('page.header.error')}</h2>
    <p>
    ${exception!'Unknown error'}
    </p>
</div>
<form></form>
</@layout.indexmaster>
