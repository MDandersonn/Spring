package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    //데이터소스가 필요  스프링이 자동으로 데이터소스인젝션해줌
    //@Autowired
    //생성자가 딱 하나만있으면 Autowired생략가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");//테이블명 ,  아이디입력

        Map<String, Object> parameters = new HashMap<>();//회원명단배열작성
        parameters.put("name", member.getName());//이름입력

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id= ?", memberRowMapper(),id);
        //jdbctemplate 에서 쿼리날려서 그결과를 rowmapper로 맵핑하고 그걸 리스트로 받아서 옵셔널로 반환
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        //결과를 RowMapper로 맵핑을 해줘야함
        return result.stream().findAny();//옵셔널로 반환
    }
    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));//resultSet에서 id와 name을 가져옴
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
//    private RowMapper<Member> memberRowMapper() {
//    //람다로 바꾼형태
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        };
//    }
}