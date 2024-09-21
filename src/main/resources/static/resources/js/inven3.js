function cascade_checkbox(source, tag) {
    const form = source.closest("form")
    const checkboxes = form.querySelectorAll(`:scope ${tag}`);
    for (let i = 0; i < checkboxes.length; i++)
        checkboxes[i].checked = source.checked
}

function close_modal_box(source) {
    const modal_box = source.closest(".modal");
    modal_box.style.display = "none";
}