// DOMが完全に読み込まれたときに処理を開始
document.addEventListener('DOMContentLoaded', () => {
  // セレクトボックスを取得（IDが 'js_selectToggle' の要素）
  const selectToggle = document.getElementById('js_selectToggle');

  // セレクトボックスが存在する場合にのみ処理を続行
  if (selectToggle) {
    // セレクトボックスの初期値を取得
    const initialToggleVal = selectToggle.value;
    console.log("初期選択値:", initialToggleVal);

    // 初期状態の設定：セレクトボックスの値に応じて関連する要素にクラスを付ける
    document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
      // 現在の要素のIDがセレクトボックスの初期値と一致するか確認
      const isActive = selectCont.id === initialToggleVal;
      // 一致する要素に 'is_active' クラスを付与、一致しない場合は削除
      selectCont.classList.toggle('is_active', isActive);
    });

    // セレクトボックスの値が変更されたときに実行する処理を設定
    selectToggle.addEventListener('change', () => {
      // セレクトボックスの新しい値を取得
      const toggleVal = selectToggle.value;
      console.log("変更後の選択値:", toggleVal);

      // すべての '.bl_selectCont' 要素をチェック
      document.querySelectorAll('.bl_selectCont').forEach(selectCont => {
        // 現在の要素のIDがセレクトボックスの値と一致するか確認
        const isActive = selectCont.id === toggleVal;
        // 一致する要素に 'is_active' クラスを付与、一致しない場合は削除
        selectCont.classList.toggle('is_active', isActive);
      });
    });
  }
});
