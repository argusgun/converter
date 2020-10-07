<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Converter</title>
</head>
<body>
<h4>Converter</h4>
<h3>${date}</h3>
<form method="post" action="/">
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

</body>
</html>