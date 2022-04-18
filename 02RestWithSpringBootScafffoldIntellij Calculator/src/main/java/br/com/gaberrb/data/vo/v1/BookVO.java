package br.com.gaberrb.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "title", "description", "price", "stars"})
public class BookVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;


    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String title;

    private String description;

    private Double price;

    private Double stars;

    public BookVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookVO)) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Objects.equals(getKey(), bookVO.getKey()) && Objects.equals(getTitle(), bookVO.getTitle()) && Objects.equals(getDescription(), bookVO.getDescription()) && Objects.equals(getPrice(), bookVO.getPrice()) && Objects.equals(getStars(), bookVO.getStars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getTitle(), getDescription(), getPrice(), getStars());
    }
}
