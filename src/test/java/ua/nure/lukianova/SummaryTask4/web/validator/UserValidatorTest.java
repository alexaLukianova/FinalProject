package ua.nure.lukianova.SummaryTask4.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.service.UserService;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    private static final int UNDER_LIMIT_TEXT_BOUND = 46;
    private static final int PASSWORD_UPPER_LIMIT = 13;
    private static final String USERNAME = "username";
    private Validator validator;
    private UserValidatorBean user;
    private User duplicateUser;
    private Map<String, String> errors;
    private static final String USERNAME_REQUIRED = "error.username.required";
    private static final String USERNAME_SHORT = "error.username.short";
    private static final String USERNAME_OCCUPIED = "error.username.occupied";
    private static final String USERNAME_LONG = "error.username.long";
    private static final String USERNAME_NON_ALPHANUMERIC = "error.username.non_alphanumeric";

    private static final String FIRST_NAME_REQUIRED = "error.first_name.required";
    private static final String FIRST_NAME_SHORT = "error.first_name.short";
    private static final String FIRST_NAME_LONG = "error.first_name.long";
    private static final String FIRST_NAME_NON_ALPHABETIC = "error.first_name.non_alphabetic";

    private static final String LAST_NAME_REQUIRED = "error.last_name.required";
    private static final String LAST_NAME_SHORT = "error.last_name.short";
    private static final String LAST_NAME_LONG = "error.last_name.long";
    private static final String LAST_NAME_NON_ALPHABETIC = "error.last_name.non_alphabetic";

    private static final String PASSWORD_REQUIRED = "error.password.required";
    private static final String PASSWORD_INVALID_LENGTH = "error.password.invalid_length";
    private static final String PASSWORD_INVALID = "error.password.has_whitespace";

    private static final String REENTER_PASSWORD_REQUIRED = "error.reenterPassword.required";
    private static final String REENTER_PASSWORD_NON_EQUAL = "error.reenter.non_equal";

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        validator = new UserValidator(userService);
        createDuplicateUser();

        given(userService.findByLogin(anyString())).willReturn(null);
    }

    @After
    public void tearDown() throws Exception {
        errors.clear();
    }

    @Test
    public void validate_EmptyUser_ErrorRequired() throws Exception {
        createEmptyUser();

        errors = validator.validate(user);
        assertTrue(errors.containsValue(USERNAME_REQUIRED));
        assertTrue(errors.containsValue(FIRST_NAME_REQUIRED));
        assertTrue(errors.containsValue(LAST_NAME_REQUIRED));
        assertTrue(errors.containsValue(PASSWORD_REQUIRED));
        assertTrue(errors.containsValue(REENTER_PASSWORD_REQUIRED));
    }

    @Test
    public void validate_OccupiedUsername_ErrorOccupied() throws Exception {
        createNonValidUsernameUser();
        given(userService.findByLogin(anyString())).willReturn(duplicateUser);

        errors = validator.validate(user);

        assertTrue(errors.containsValue(USERNAME_OCCUPIED));
    }

    @Test
    public void validate_ShortTextUser_ErrorShort() throws Exception {
        createShortTextUser();

        errors = validator.validate(user);

        assertTrue(errors.containsValue(FIRST_NAME_SHORT));
        assertTrue(errors.containsValue(LAST_NAME_SHORT));
        assertTrue(errors.containsValue(USERNAME_SHORT));
    }

    @Test
    public void validate_LongTextUser_ErrorShort() throws Exception {
        createLongTextUser();

        errors = validator.validate(user);

        assertTrue(errors.containsValue(FIRST_NAME_LONG));
        assertTrue(errors.containsValue(LAST_NAME_LONG));
        assertTrue(errors.containsValue(USERNAME_LONG));
        assertTrue(errors.containsValue(PASSWORD_INVALID_LENGTH));
    }

    @Test
    public void validate_NonValidTextUser_ErrorInvalid() throws Exception {
        createInvalidTextUser();

        errors = validator.validate(user);

        assertTrue(errors.containsValue(FIRST_NAME_NON_ALPHABETIC));
        assertTrue(errors.containsValue(LAST_NAME_NON_ALPHABETIC));
        assertTrue(errors.containsValue(USERNAME_NON_ALPHANUMERIC));
        assertTrue(errors.containsValue(PASSWORD_INVALID));
    }

    @Test
    public void validate_NonValidPasswordUser_ErrorInvalid() throws Exception {
        createInvalidPasswordUser();

        errors = validator.validate(user);

        assertTrue(errors.containsValue(PASSWORD_INVALID_LENGTH));
        assertTrue(errors.containsValue(REENTER_PASSWORD_NON_EQUAL));
    }

    @Test
    public void validate_ValidUser_NoErrors() throws Exception {
        createValidUser();

        errors = validator.validate(user);

        assertTrue(errors.isEmpty());
    }

    private void createEmptyUser() {
        user = new UserValidatorBean();
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createNonValidUsernameUser() {
        user = new UserValidatorBean();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setPassword("password");
        user.setReenterPassword("password");
        user.setUsername(USERNAME);
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createShortTextUser() {
        user = new UserValidatorBean();
        user.setFirstName("f");
        user.setLastName("l");
        user.setPassword("password");
        user.setReenterPassword("password");
        user.setUsername("u");
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createLongTextUser() {
        user = new UserValidatorBean();
        user.setFirstName(StringUtils.rightPad("first name", UNDER_LIMIT_TEXT_BOUND, '*'));
        user.setLastName(StringUtils.rightPad("last name", UNDER_LIMIT_TEXT_BOUND, '*'));
        user.setPassword(StringUtils.rightPad("password", PASSWORD_UPPER_LIMIT, '*'));
        user.setReenterPassword(StringUtils.rightPad("password", PASSWORD_UPPER_LIMIT, '*'));
        user.setUsername(StringUtils.rightPad("username", UNDER_LIMIT_TEXT_BOUND, '*'));
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createDuplicateUser() {
        duplicateUser = new User();
        duplicateUser.setUsername(USERNAME);

    }

    private void createInvalidTextUser() {
        user = new UserValidatorBean();
        user.setFirstName("111first name");
        user.setLastName("111last name");
        user.setPassword("pass   word");
        user.setReenterPassword("pass   word");
        user.setUsername("_+(username");
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createInvalidPasswordUser() {
        user = new UserValidatorBean();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setPassword("p");
        user.setReenterPassword("password");
        user.setUsername("username");
        user.setRoleId(1);
        user.setId(1l);
    }

    private void createValidUser() {
        user = new UserValidatorBean();
        user.setFirstName("first na-me");
        user.setLastName("last-name");
        user.setPassword("password");
        user.setReenterPassword("password");
        user.setUsername("username111");
        user.setRoleId(1);
        user.setId(1l);
    }
}
