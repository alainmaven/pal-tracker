package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(MysqlDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String addQuery = "INSERT INTO time_entries(project_id, user_id, date, hours)"
                + " VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(addQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setString(3, timeEntry.getDate().toString());
            statement.setInt(4, timeEntry.getHours());

            return statement;
        }, keyHolder);


        return find(keyHolder.getKey().longValue());
    }

    @Override
    public TimeEntry find(Long id) {

        String searchQuery = "SELECT * FROM time_entries WHERE id = ?";
        TimeEntry timeEntry;
        try {
             timeEntry = jdbcTemplate
                    .query(
                            searchQuery,
                            (rs, rowNum) -> new TimeEntry(
                                    rs.getLong("id"),
                                    rs.getLong("project_id"),
                                    rs.getLong("user_id"),
                                    rs.getDate("date").toLocalDate(),
                                    rs.getInt("hours")
                            ),
                            id
                    )
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Time entry not found"));
        }
        catch (Exception e) {
            return null;
        }
        return timeEntry;
    }

    @Override
    public List<TimeEntry> list() {
        String searchQuery = "SELECT * FROM time_entries";
        List<TimeEntry> timeEntry;
        try {
            timeEntry = jdbcTemplate
                    .query(
                            searchQuery,
                            (rs, rowNum) -> new TimeEntry(
                                    rs.getLong("id"),
                                    rs.getLong("project_id"),
                                    rs.getLong("user_id"),
                                    rs.getDate("date").toLocalDate(),
                                    rs.getInt("hours")
                            )
                    );
        }
        catch (Exception e) {
            return null;
        }
        return timeEntry;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {


        String addQuery = "UPDATE time_entries SET project_id =?, user_id=? , date=?, hours=?"
                + " WHERE id=?";

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(addQuery);
            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setString(3, timeEntry.getDate().toString());
            statement.setInt(4, timeEntry.getHours());
            statement.setLong(5, id);

            return statement;
        });

        return find(id);
    }

    @Override
    public void delete(Long id) {
        String addQuery = "DELETE from time_entries WHERE id=?";

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(addQuery);
            statement.setLong(1, id);
            return statement;
        });
    }
}
