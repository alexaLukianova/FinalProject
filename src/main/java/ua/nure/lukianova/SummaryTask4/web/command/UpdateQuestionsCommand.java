//package ua.nure.lukianova.SummaryTask4.web.command;
//
//import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
//import ua.nure.lukianova.SummaryTask4.db.entity.Question;
//import ua.nure.lukianova.SummaryTask4.exception.AppException;
//import ua.nure.lukianova.SummaryTask4.web.Path;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UpdateQuestionsCommand extends Command {
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
//
//        long questionId = Long.valueOf(request.getParameter("question_id"));
//
//        request.setAttribute("test_id", request.getParameter("test_id"));
//
//        getQuestionService().update(extractQuestion(request));
//
//        for (Answer answer : extractListOfAnswers(request)) {
//            getAnswerService().update(answer);
//        }
//
//
//        // List<Answer> answers = getAnswerService().findByQuestionId(questionId);
//
////        int i = 0;
////        for (Answer answer : answers) {
////            boolean correct = "is_correct".equals(request.getParameter("correct" + i));
////
////            getAnswerService().update(answer.getId(), request.getParameter("answer" + i), correct);
////            i++;
////        }
//
//        return Path.COMMAND_EDIT_TEST;
//    }
//
//    private Question extractQuestion(HttpServletRequest request) {
//        Question question = new Question();
//        question.setId(Long.valueOf(request.getParameter("question_id")));
//        question.setText(request.getParameter("question"));
//        question.setTestId(Long.valueOf(request.getParameter("test_id")));
//        return question;
//    }
//
//    private List<Answer> extractListOfAnswers(HttpServletRequest request) {
//        String[] text = request.getParameterValues("answer");
//        String[] correct = request.getParameterValues("correct");
//        String[] ids = request.getParameterValues("answer_id");
//        Answer answer;
//        List<Answer> answers = new ArrayList<>();
//        for (int i = 0; i < ids.length; i++) {
//            answer = new Answer();
//            answer.setId(Long.valueOf(ids[i]));
//            answer.setText(text[i]);
//            answer.setQuestionId(Long.valueOf(request.getParameter("question_id")));
//            answer.setCorrect("is_correct".endsWith(correct[i]));
//            answers.add(answer);
//        }
//
//        return answers;
//    }
//}
