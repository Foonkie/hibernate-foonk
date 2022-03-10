package com.foonk;

import com.foonk.entity.Company;
import com.foonk.entity.User;
import com.foonk.entity.UserChat;
import com.foonk.entity.UserChat;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            session.enableFetchProfile("withCompanyAndPayment");

            var userGraph = session.createEntityGraph(User.class);
            userGraph.addAttributeNodes("company", "userChats");
            var userChatsSubgraph = userGraph.addSubgraph("userChats", UserChat.class);
            userChatsSubgraph.addAttributeNodes("chat");

            Map<String, Object> properties = Map.of(
//                    GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat")
                    GraphSemantic.LOAD.getJpaHintName(), userGraph
            );
            var user = session.find(User.class, 1L, properties);
            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());
//            System.out.println(user.getPayments().size());

            var users = session.createQuery(
                    "select u from User u " +
                            "where 1 = 1", User.class)
//                    .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat"))
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
                    .list();
            users.forEach(it -> System.out.println(it.getUserChats().size()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            session.getTransaction().commit();
        }
    }
}
