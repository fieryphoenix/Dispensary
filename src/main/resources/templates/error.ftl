<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<@layout.indexmaster titleKey="page.title.error">
<div class="page-header">

    <h2>${springMacroRequestContext.getMessage('page.header.error')}</h2>
    <p>print nice error</p>
    <p>
    ${exception!''}
    </p>
</div>
<form></form>
</@layout.indexmaster>
