package ua.nure.lukianova.SummaryTask4.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.nure.lukianova.SummaryTask4.web.command.AddTestCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.DeleteQuestionCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.DeleteTestCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.DeleteUserCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.EvaluateResultCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ListTestsCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ListUsersCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.LockUserCommand;
import ua.nure.lukianova.SummaryTask4.web.command.LockUserCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.LoginCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.LogoutCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.NoCommand;
import ua.nure.lukianova.SummaryTask4.web.command.NoCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.RegisterCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.RunTestCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.SaveNewTestCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.SaveQuestionCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ShowEditFormClassTest;
import ua.nure.lukianova.SummaryTask4.web.command.ShowProfileCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ShowRegisterCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ShowResultCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.ShowSaveQuestionCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.UpdateQuestionInfoCommandTest;
import ua.nure.lukianova.SummaryTask4.web.command.UpdateTestInfoCommandTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AddTestCommandTest.class,
        DeleteQuestionCommandTest.class,
        DeleteTestCommandTest.class,
        DeleteUserCommandTest.class,
        EvaluateResultCommandTest.class,
        ListTestsCommandTest.class,
        ListUsersCommandTest.class,
        LockUserCommandTest.class,
        LoginCommandTest.class,
        LogoutCommandTest.class,
        NoCommandTest.class,
        RegisterCommandTest.class,
        RunTestCommandTest.class,
        SaveNewTestCommandTest.class,
        SaveQuestionCommandTest.class,
        ShowEditFormClassTest.class,
        ShowProfileCommandTest.class,
        ShowRegisterCommandTest.class,
        ShowResultCommandTest.class,
        ShowSaveQuestionCommandTest.class,
        UpdateQuestionInfoCommandTest.class,
        UpdateTestInfoCommandTest.class
})
public class CommandTestSuite {
}
