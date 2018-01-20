package ua.nure.lukianova.SummaryTask4.web;

public final class Path {

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ADMIN_LANDING = "/WEB-INF/jsp/admin/landing.jsp";
    public static final String PAGE_LIST_USERS = "/WEB-INF/jsp/admin/listUsers.jsp";
    public static final String PAGE_LIST_TESTS = "/WEB-INF/jsp/admin/listTests.jsp";
    public static final String PAGE_ADD_TEST = "/WEB-INF/jsp/admin/testAdd.jsp";
    public static final String PAGE_EDIT_TEST = "/WEB-INF/jsp/admin/editTest.jsp";

    public static final String PAGE_ADD_QUESTIONS = "/WEB-INF/jsp/admin/questionAdd.jsp";

    public static final String COMMAND_LIST_TESTS = "/controller?command=listTests";
    public static final String COMMAND_LIST_USERS = "/controller?command=listUsers";

    public static final String COMMAND_EDIT_TEST = "/controller?command=editTest";

    public static final String PAGE_ADD_NEW_QUESTION = "/WEB-INF/jsp/admin/addNewQuestion.jsp";






    public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands

    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";

    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/admin/error_page.jsp";
}
