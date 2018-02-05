package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ListTestsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ListTestsCommandTest {

    private Command command;
    private static final String EXPECTED_PATH = Path.PAGE_LIST_TESTS;
    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> tests;
    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> ascTests;
    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> descTests;

    @Mock
    private QuestionService questionService;
    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    private ArgumentCaptor<Object> questionCountCaptor;
    @Captor
    private ArgumentCaptor<List<ua.nure.lukianova.SummaryTask4.db.entity.Test>> testsCaptor;

    @Before
    public void setUp() throws Exception {
        command = new ListTestsCommand(testService, questionService);

        tests = createListTests();
        ascTests = createAscListTests();
        descTests = createDescListTests();

        given(request.getSession()).willReturn(session);
        given(testService.findAllTests()).willReturn(tests);
        given(session.getAttribute(anyString())).willReturn(Role.ADMIN);
        given(questionService.findByTestId(anyLong())).willReturn(createListQuestions(3));

        doNothing().when(request).setAttribute(eq(Parameter.TESTS), testsCaptor.capture());
        doNothing().when(request).setAttribute(eq(Parameter.QUESTIONS_COUNT), questionCountCaptor.capture());
    }

    @Test
    public void execute_FindForAdmin_AllTests() throws Exception {
        given(session.getAttribute(anyString())).willReturn(Role.ADMIN);

        command.execute(request, response);

        verify(testService).findAllTests();
    }

    @Test
    public void execute_FindForStudent_TestsWithQuestions() throws Exception {
        given(session.getAttribute(anyString())).willReturn(Role.STUDENT);

        command.execute(request, response);

        verify(testService).findAllWithQuestions();
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertEquals(EXPECTED_PATH, actualPath);
    }

    @Test
    public void execute_SetTestsIntoRequest_True() throws Exception {
        command.execute(request, response);

        assertThat(testsCaptor.getValue(), equalTo(tests));
    }

    @Test
    public void execute_SetQuestionCountIntoRequest_True() throws Exception {
        command.execute(request, response);

        assertThat((Map<Long, Integer>) questionCountCaptor.getValue(), equalTo(getQuestionCount()));
    }

    @Test
    public void execute_SortTestByNameDesc_True() throws Exception {
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_NAME);
        given(request.getParameter(eq(Parameter.ORDER))).willReturn(Parameter.DESC_ORDER);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(descTests));
    }

    @Test
    public void execute_SortTestByNameAsc_True() throws Exception {
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_NAME);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(ascTests));
    }

    @Test
    public void execute_SortTestBySubjectDesc_True() throws Exception {
        given(request.getParameter(eq(Parameter.ORDER))).willReturn(Parameter.DESC_ORDER);
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_SUBJECT);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(descTests));
    }

    @Test
    public void execute_SortTestBySubjectAsc_True() throws Exception {
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_SUBJECT);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(ascTests));
    }

    @Test
    public void execute_SortTestByDurationDesc_True() throws Exception {
        given(request.getParameter(eq(Parameter.ORDER))).willReturn(Parameter.DESC_ORDER);
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_DURATION);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(descTests));
    }

    @Test
    public void execute_SortTestByDurationAsc_True() throws Exception {
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_DURATION);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(ascTests));
    }

    @Test
    public void execute_SortTestByComplexityIdDesc_True() throws Exception {
        given(request.getParameter(eq(Parameter.ORDER))).willReturn(Parameter.DESC_ORDER);
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_COMPLEXITY_ID);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(descTests));
    }

    @Test
    public void execute_SortTestByComplexityIdAsc_True() throws Exception {
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.TEST_COMPLEXITY_ID);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(ascTests));
    }

    @Test
    public void execute_SortTestByQuestionQuantityAsc_True() throws Exception {
        given(questionService.findByTestId(eq(1l))).willReturn(createListQuestions(1));
        given(questionService.findByTestId(eq(2l))).willReturn(createListQuestions(2));
        given(questionService.findByTestId(eq(3l))).willReturn(createListQuestions(3));
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.QUESTIONS_COUNT);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(ascTests));
    }

    @Test
    public void execute_SortTestByQuestionQuantityDesc_True() throws Exception {
        given(questionService.findByTestId(eq(1l))).willReturn(createListQuestions(1));
        given(questionService.findByTestId(eq(2l))).willReturn(createListQuestions(2));
        given(questionService.findByTestId(eq(3l))).willReturn(createListQuestions(3));
        given(request.getParameter(eq(Parameter.PARAMETER))).willReturn(Parameter.QUESTIONS_COUNT);
        given(request.getParameter(eq(Parameter.ORDER))).willReturn(Parameter.DESC_ORDER);

        command.execute(request, response);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> actualListTests = testsCaptor.getValue();

        assertThat(actualListTests, equalTo(descTests));
    }

    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> createListTests() {
        ua.nure.lukianova.SummaryTask4.db.entity.Test test1 =
                createTest(1l, "Name A", 10, "Subject A", 0);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test2 =
                createTest(3l, "Name C", 30, "Subject C", 2);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test3 =
                createTest(2l, "Name B", 20, "Subject B", 1);

        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> tests = new LinkedList<>();
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);

        return tests;
    }

    private ua.nure.lukianova.SummaryTask4.db.entity.Test createTest(Long id, String name, int duration, String subject,
                                                                     int complexityId) {
        ua.nure.lukianova.SummaryTask4.db.entity.Test test = new ua.nure.lukianova.SummaryTask4.db.entity.Test();
        test.setId(id);
        test.setName(name);
        test.setDuration(duration);
        test.setSubject(subject);
        test.setComplexityId(complexityId);

        return test;
    }

    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> createDescListTests() {
        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> tests = new LinkedList<>();
        ua.nure.lukianova.SummaryTask4.db.entity.Test test1 =
                createTest(3l, "Name C", 30, "Subject C", 2);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test2 =
                createTest(2l, "Name B", 20, "Subject B", 1);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test3 =
                createTest(1l, "Name A", 10, "Subject A", 0);

        tests.add(test1);
        tests.add(test2);
        tests.add(test3);

        return tests;
    }

    private List<ua.nure.lukianova.SummaryTask4.db.entity.Test> createAscListTests() {
        List<ua.nure.lukianova.SummaryTask4.db.entity.Test> tests = new LinkedList<>();

        ua.nure.lukianova.SummaryTask4.db.entity.Test test1 =
                createTest(1l, "Name A", 10, "Subject A", 0);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test2 =
                createTest(2l, "Name B", 20, "Subject B", 1);

        ua.nure.lukianova.SummaryTask4.db.entity.Test test3 =
                createTest(3l, "Name C", 30, "Subject C", 2);

        tests.add(test1);
        tests.add(test2);
        tests.add(test3);

        return tests;
    }

    private List<Question> createListQuestions(int count) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Question question = new Question();
            question.setId(Long.valueOf(i));
            questions.add(question);
        }

        return questions;
    }

    private Map<Long, Integer> getQuestionCount() {
        int questionCount = createListQuestions(3).size();
        Map<Long, Integer> map = new HashMap<>();
        for (ua.nure.lukianova.SummaryTask4.db.entity.Test test : createListTests()) {
            map.put(test.getId(), questionCount);
        }
        return map;
    }

}
