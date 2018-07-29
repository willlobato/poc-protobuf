package net.pupunha.poc.protobuf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Employee {
    @JsonProperty(required = true)
    public String name;

    @JsonProperty(required = true)
    public int age;
    public String[] emails;
    public Employee boss;
    private Set<Policy> policy;

}
