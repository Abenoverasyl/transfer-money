package com.app.transfermoney.controller;


import com.app.transfermoney.model.TransferMoneyRequest;
import com.app.transfermoney.model.User;
import com.app.transfermoney.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping(value = "/all")
    public List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public User findByName(@PathVariable final String name) {
        return userJpaRepository.findByName(name);
    }

    @GetMapping(value = "/account/{account}")
    public User findByAccount(@PathVariable final String account) {
        if (account == null || account.length() == 0) {
            return null;
        }
        return userJpaRepository.findByAccount(account);
    }

    @PostMapping(value = "/load")
    public User load(@RequestBody final User user) {
        userJpaRepository.save(user);
        return userJpaRepository.findByName(user.getName());
    }

    @PostMapping(value = "/transfer")
    public String transferMoney(TransferMoneyRequest transferMoneyRequest) {
        User fromUser = findByAccount(transferMoneyRequest.getFromAccount());
        User toUser = findByAccount(transferMoneyRequest.getToAccount());

        String checkTransferDataResult = checkDataTransfer(transferMoneyRequest, fromUser, toUser);

        if (checkTransferDataResult != "OK") {
            return checkTransferDataResult;
        }

        String makeTransferResult = makeTransfer(transferMoneyRequest, fromUser, toUser);

        return makeTransferResult;
    }

    public String makeTransfer(TransferMoneyRequest transferMoneyRequest, User fromUser, User toUser) {
        Long fromMoney = fromUser.getMoney() - transferMoneyRequest.getMoney();
        Long toMoney = toUser.getMoney() + transferMoneyRequest.getMoney();

        if (updateUser(transferMoneyRequest.getFromAccount(), fromMoney) != "OK") {
            return "Can't transfer";
        }
        if (updateUser(transferMoneyRequest.getToAccount(), toMoney) != "OK") {
            return "Can't transfer";
        }

        return "OK";
    }

    public String checkDataTransfer(TransferMoneyRequest transferMoneyRequest, User fromUser, User toUser) {
        if (transferMoneyRequest.getMoney() < 100) {
            return "Деньги не может быть меньше 100 тг";
        }

        if (fromUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getFromAccount());
        }

        if (toUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getToAccount());
        }

        if (fromUser.getMoney() < transferMoneyRequest.getMoney()) {
            return MessageFormat.format("У клиента \"{0}\" не хватает деньги на счету", fromUser.getName());
        }
        return "OK";
    }

    public String updateUser(String account, Long money) {
        User user = userJpaRepository.findByAccount(account);
        user.setMoney(money);
        userJpaRepository.save(user);
        return "OK";
    }
}
