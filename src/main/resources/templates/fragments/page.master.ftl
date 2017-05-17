<#include "head.ftl" />

<#macro page_head>
</#macro>

<#macro indexmaster titleKey="defaultTitle">
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
    <div class="page-header">
        <#nested />
    </div>
</div>

    <#include "footer.ftl" />
</body>
</html>
</#macro>