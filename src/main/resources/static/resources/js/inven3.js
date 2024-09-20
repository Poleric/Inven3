function cascade_checkbox(source, tag) {
    const checkboxes = document.querySelectorAll(tag);
    for (let i = 0; i < checkboxes.length; i++)
        checkboxes[i].checked = source.checked
}