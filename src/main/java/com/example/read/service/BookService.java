package com.example.read.service;

import com.example.read.dto.BookDTO;
import com.example.read.entity.Book;
import com.example.read.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @KafkaListener(topics = "book-create-topic", groupId = "read-service-group")
    public void consumeBookEvent(BookDTO dto) {
        log.info("Kafka 데이터 수신 완료: {}", dto.getTitle());

        Book book = Book.builder()
                .bid(dto.getBid())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .category(dto.getCategory())
                .pages(dto.getPages())
                .price(dto.getPrice())
                .published_date(dto.getPublished_date())
                .description(dto.getDescription())
                .build();

        bookRepository.save(book);
        log.info("MongoDB 저장 완료. (원본 MySQL ID: {})", book.getBid());
    }
}
