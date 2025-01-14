package com.example.Budget.Management.controller;


import com.example.Budget.Management.entity.User;
import com.example.Budget.Management.service.UserMangementService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ユーザー情報を管理するコントローラー
 */

@Controller
@RequestMapping("/usermanage")
@RequiredArgsConstructor
public class UserMangementController {
    /** DI*/
    private final UserMangementService service;

    /**
     * メニュー画面
     */
    @GetMapping("/")
    public String getMenu(){
        return "usermanage/usermenu";
    }

    /**
     * ユーザー情報一覧をmodelに入れて、Viewを返す
     */
    @GetMapping("/userlist")
    public String getUserinf(Model model){
        model.addAttribute("users", service.searchUserinf());
        return "usermanage/userlist";
    }



    /**削除処理*/
    @GetMapping("/userlist-delete")
    public String getUserinfdelete(Model model){
        model.addAttribute("users", service.searchUserinf());
        return "usermanage/userlist-delete";
    }
    /**
     * ViewからIDを受け取り、そのID番号のユーザー情報を取得し、画面に変えす（削除確認）
     */
    @GetMapping("/delete/{id}")
    public String deleteconfirm(@PathVariable String id, Model model){
        model.addAttribute("userInf", service.searchUserinfByid(Integer.parseInt(id)));
        return "usermanage/deleteuser";

    }

    /**
     * ViewからIDを受け取り、そのID番号のユーザーを論理削除
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.deleteUser(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("message", "削除が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/usermanage/userlist-delete";
    }


    /**編集処理*/
    @GetMapping("/userlist-edit")
    public String getUserinfedit(Model model){
        model.addAttribute("users", service.searchAllUserinf());
        return "usermanage/userlist-edit";
    }
    /**
     *ViewからIDを受け取り、そのIDのユーザーの編集を行う
     */
    @GetMapping("/edit/{id}")
    public String  editUser(@PathVariable String id,Model model){
        User userInf = new User();
        userInf = service.searchUserinfByid(Integer.parseInt(id));

        model.addAttribute("userInf", userInf);
        return "usermanage/edit";

    }

    /**
     * ViewからIDを受け取り、そのID番号のユーザー情報を取得し、画面に変えす（編集確認）
     */
    @PostMapping("/edit/confirm")
    public String confirmEdit(@ModelAttribute("userInf") User user, Model model) {
        if(user.getDeleteFlag() == null){
            user.setDeleteFlag(0);
        }

        model.addAttribute("userInf", user); // 確認画面用のデータを渡す
        return "usermanage/confirm";
    }


    @PostMapping("/edit/execute")
    public String updateUser(@ModelAttribute User user
            , BindingResult result,Model model,  RedirectAttributes redirectAttributes){

        service.updateUserinf(user);
        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/usermanage/userlist-edit";
    }

    /**ユーザーの新規登録*/

    /**
     *登録画面の表示
     */
    @GetMapping("/registeruser")
    public String getUserdisplay(Model model){
        model.addAttribute("user", new User());
        return "usermanage/register";
    }

    /**
     * 登録画面で入力した値で確認画面を表示
     */
    @PostMapping("/register/confirm")
    public String confirmRegister(@ModelAttribute("user") User user,
                                  Model model) {
        try{
            service.insertcheck(user);
        }catch (DataIntegrityViolationException ex) {
            // マルチキャッチ構文で例外を処理
            model.addAttribute("errorMessage", ex.getMessage());
            return "usermanage/register"; // エラーメッセージを登録画面に表示
        }
        model.addAttribute("user", user); // 確認画面用のデータを渡す
        return "usermanage/registerconfirm";
    }

    /**
     * 登録
     */
    @PostMapping("/register/execute")
    public String registerUser(@ModelAttribute User user
            , BindingResult result,Model model,  RedirectAttributes redirectAttributes){

            service.insertUsrinf(user);
            redirectAttributes.addFlashAttribute("message", "登録が完了しました");
            //mapping(java側にリダイレクトしている)
            return "redirect:/usermanage/userlist";


    }


}
