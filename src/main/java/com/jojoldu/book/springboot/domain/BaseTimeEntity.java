package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
// JPA Entity 클래스 들이 BaseTimeEntity 클래스 상속시
// createDate, modifiedDate 필드도 컬럼으로 인식

@EntityListeners(AuditingEntityListener.class)
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킴

public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될때 시간이 자동 저장됨
    private LocalDateTime createdDate;
    @LastModifiedDate // 조회한 Entity 값을 변경할때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;
}
