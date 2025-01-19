// 金額をカンマ区切りにフォーマットする関数
const formatNumberWithCommas = (input) => {
    const cursorPosition = input.selectionStart;
    let rawValue = input.value.replace(/,/g, '').replace(/[^0-9]/g, ''); // 数値以外を削除
    input.value = new Intl.NumberFormat().format(rawValue); // カンマ区切りを適用
    input.setSelectionRange(cursorPosition, cursorPosition); // カーソル位置を保持
};

// 送信時にコンマを削除する関数
const removeCommas = (form) => {
    const amountInputs = form.querySelectorAll('.js-format-amount');
    amountInputs.forEach(input => {
        input.value = input.value.replace(/,/g, ''); // コンマを削除
    });
};

// ページ読み込み時に処理を初期化
document.addEventListener('DOMContentLoaded', () => {
    // 金額入力欄にフォーマットを適用
    const amountInputs = document.querySelectorAll('.js-format-amount');
    amountInputs.forEach(input => {
        input.addEventListener('input', () => formatNumberWithCommas(input)); // 入力時にフォーマット

        // 初期値があればフォーマットを適用
        if (input.value) {
            input.value = new Intl.NumberFormat().format(input.value.replace(/,/g, ''));
        }
    });

    // フォーム送信時にコンマを削除
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', () => {
            removeCommas(form); // 送信前にコンマを削除
        });
    });
});
