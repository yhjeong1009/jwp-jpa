package jpa.repository;

import jpa.entity.Line;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class LineRepositoryTest {
    @Autowired
    LineRepository lineRepository;

    @Test
    @DisplayName("저장test")
    void save() {
        Line line = new Line();
        Line result = lineRepository.save(line);
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("색으로 저장, 조회, 수정")
    void findBycolor() {
        Line line = new Line("green","two");
        Line result = lineRepository.save(line);
        assertAll(
                () -> assertThat(result.getColor()).isEqualTo("green"),
                () -> assertThat(result).isEqualTo(lineRepository.findByColor("green")),
                () -> assertThat(result.getColor()).isEqualTo(lineRepository.findByColor("two").getColor())
        );
        result.setColor("blue");
        assertThat(result.getColor()).isEqualTo(lineRepository.findByColor("blue").getColor());
    }

    @Test
    @DisplayName("이름으로 저장, 조회, 수정")
    void findByName() {
        Line line = new Line("purple","five");
        Line result = lineRepository.save(line);
        assertAll(
                () -> assertThat(result.getName()).isEqualTo("five"),
                () -> assertThat(result).isEqualTo(lineRepository.findByName("five")),
                () -> assertThat(result.getName()).isEqualTo(lineRepository.findByName("five").getName())
        );
        result.setName("one");
        assertThat(result.getName()).isEqualTo(lineRepository.findByName("one").getName());
    }
}
