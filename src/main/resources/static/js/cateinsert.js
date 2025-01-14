document.addEventListener('DOMContentLoaded', () => {
  const selectToggle = document.getElementById('js_selectToggle'); // セレクトボックス取得
  if (selectToggle) {
    const initialToggleVal = selectToggle.value; // セレクトボックスの初期値取得
    console.log("初期選択値:", initialToggleVal);

    // 初期状態の設定
    document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
      const isActive = selectCont.id === initialToggleVal; // IDが一致するか確認
      selectCont.classList.toggle('is_active', isActive); // クラスを付与・削除
    });

    // セレクトボックス変更時の処理
    selectToggle.addEventListener('change', () => {
      const toggleVal = selectToggle.value;
      console.log("変更後の選択値:", toggleVal);
      document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
        const isActive = selectCont.id === toggleVal;
        selectCont.classList.toggle('is_active', isActive); // クラスを付与・削除
      });
    });
  }
});
