<#include "head.ftl" />

<#macro page_head>
</#macro>

<#macro indexmaster nestedOut="" titleKey="defaultTitle">
<!DOCTYPE html>
<html lang="ru">
<head>
    <@common_page_head/>
    <@page_head/>
    <title>${springMacroRequestContext.getMessage(titleKey)}</title>
</head>
<body>
    <#include "navigation.ftl" />

<div class="container">
    <#if msgKey??>
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${springMacroRequestContext.getMessage(msgKey)}</strong>
        </div>
    </#if>
    <div class="col-md-12">
        <#if nestedOut?has_content>
            ${nestedOut}
        <#else>
            <#nested />
        </#if>
    </div>
</div>

    <#include "footer.ftl" />
</body>
</html>
</#macro>