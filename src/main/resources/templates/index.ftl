<#import "./fragments/page.master.ftl" as layout/>

<@layout.indexmaster titleKey="page.title.home">
<div class="page-header">
    <h2>${springMacroRequestContext.getMessage('page.header.welcome')}</h2>
</div>
<script type="application/javascript">
    $("#home").addClass('active');
</script>
</@layout.indexmaster>