<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Converter</title>
</head>
<body>
<h4>Converter</h4>
<h3>${date}</h3>
<form method="post" action="/converter">
<p><input type="text" name="countFrom"><select name="listValue1">
        <#list list as r>
            <#if lv1==r.name>
                <option selected>${r.name}</option>
            <#else><option>${r.name}</option>
            </#if>
        </#list>
    </select></p>
    <p><select name="listValue2">
            <#list list as r>
                <#if lv2==r.name>
                    <option selected>${r.name}</option>
                <#else><option>${r.name}</option>
                </#if>
            </#list>
        </select></p>
    <p><label>${result}</label></p>
    <button type="submit">Конвертировать</button>
</form>
<#if user??>
<h2>История конвертаций</h2>
<#if list??>
<#list clist as l>
<div>${l.time} ${l.log}</div>
</#list>
</#if>
<button type="button"><a href="/logout">Выйти</a></button>
<#else><button type="button"><a href="/login">Войти через Google</a></button>
</#if>
</body>
</html>