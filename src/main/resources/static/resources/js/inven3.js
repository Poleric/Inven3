function cascade_checkbox(source, tag) {
    const form = source.closest("form")
    const checkboxes = form.querySelectorAll(":scope [name='selected']");
    for (let i = 0; i < checkboxes.length; i++)
        checkboxes[i].checked = source.checked
}