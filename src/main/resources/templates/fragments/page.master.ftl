<#include "head.ftl" />

<#macro page_head>
</#macro>

<#macro indexmaster title="defaultTitle">
<!DOCTYPE html>
<html lang="en">
<head>
    <@common_page_head/>
    <@page_head/>
    <title>${title}</title>
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