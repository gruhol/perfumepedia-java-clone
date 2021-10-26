package org.perfumepedia.DataBase.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteNoteStat {
    private Long idNote;
    private Integer value;
}
