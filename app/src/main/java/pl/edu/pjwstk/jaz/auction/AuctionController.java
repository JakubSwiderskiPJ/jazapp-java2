package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.Models.Auction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "auctioncontrller",
        urlPatterns = "/auction"
)
@Named
@RequestScoped
public class AuctionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuctionService auctionService;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        auctionService = new AuctionService(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertAuction(request, response);
                    break;
                case "/delete":
                    deleteAuction(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAuction(request, response);
                    break;
                default:
                    listAuctions(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAuctions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Auction> listAuctions = auctionService.listAllAuctions();
        request.setAttribute("listAuctions", listAuctions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("auctionList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("auction.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Auction existingAuction = auctionService.getAuction(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("auctionIndex.jsp");
        request.setAttribute("auction", existingAuction);
        dispatcher.forward(request, response);
    }

    private void insertAuction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Auction newAuction = new Auction(title, author, price);
        auctionService.insertAuction(newAuction);
        response.sendRedirect("list");
    }

    private void updateAuction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Auction auction = new Auction(id, title, author, price);
        auctionService.updateAuction(auction);
        response.sendRedirect("list");
    }

    private void deleteAuction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Auction auction = new Auction(id);
        auctionService.deleteAuction(auction);
        response.sendRedirect("list");

    }
}