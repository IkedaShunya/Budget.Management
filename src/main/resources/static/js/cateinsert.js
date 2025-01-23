// DOMが完全に読み込まれたときに処理を開始
document.addEventListener('DOMContentLoaded', () => {
  // セレクトボックスを取得（IDが 'js_selectToggle' の要素）
  const selectToggle = document.getElementById('js_selectToggle');

  // セレクトボックスが存在する場合にのみ処理を続行
  if (selectToggle) {
    // セレクトボックスの初期値を取得
    const initialToggleVal = selectToggle.value;

    // 初期状態の設定
    document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
      const isActive = selectCont.id === initialToggleVal;
      selectCont.classList.toggle('is_active', isActive);
    });

    // セレクトボックスの値が変更されたときに実行
    selectToggle.addEventListener('change', () => {
      const toggleVal = selectToggle.value;

      // すべての '.bl_selectCont' 要素をチェック
      document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
        const isActive = selectCont.id === toggleVal;
        selectCont.classList.toggle('is_active', isActive);
      });
    });
  }

  // 金額をカンマ区切りにフォーマットする関数
  const formatNumberWithCommas = (input) => {
    const cursorPosition = input.selectionStart;
    let rawValue = input.value.replace(/,/g, '').replace(/[^0-9]/g, '');
    input.value = new Intl.NumberFormat().format(rawValue);
    input.setSelectionRange(cursorPosition, cursorPosition);
  };

  // 送信時にコンマを削除する関数
  const removeCommas = (form) => {
    const amountInputs = form.querySelectorAll('.js-format-amount');
    amountInputs.forEach(input => {
      input.value = input.value.replace(/,/g, ''); // コンマを削除
    });
  };

  // 金額入力欄のフォーマットを初期化
  const initializeNumberFormatting = () => {
    const amountInputs = document.querySelectorAll('.js-format-amount');

    amountInputs.forEach(input => {
      input.addEventListener('input', () => formatNumberWithCommas(input));

      // 初期値にフォーマットを適用
      if (input.value) {
        input.value = new Intl.NumberFormat().format(input.value.replace(/,/g, ''));
      }
    });
  };

  // フォーム送信時の処理を設定
  const initializeFormSubmitHandler = () => {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
      form.addEventListener('submit', () => {
        removeCommas(form); // 送信前にコンマを削除
      });
    });
  };

  // 初期化処理を実行
  initializeNumberFormatting();
  initializeFormSubmitHandler();
});
