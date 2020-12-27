package com.github.eugene.msv.elastic.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Document(indexName = "person")
@TypeAlias("person")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "uid")
public class Person {

    @Id
    private String uid;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "second_name")
    private String secondName;

    @Field(name = "birt_date", format = DateFormat.year_month_day)
    private LocalDate birthDate;

    @Field(name = "addresses", type = FieldType.Nested, includeInParent = true)
    private List<Address> addresses;
}
