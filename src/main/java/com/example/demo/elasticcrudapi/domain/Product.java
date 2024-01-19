package com.example.demo.elasticcrudapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    @Field(type = FieldType.Integer)
    private int ProductID;
    @Field(type = FieldType.Text)
    private String Name;
    @Field(type = FieldType.Text)
    private String ProductNumber;
    @Field(type = FieldType.Boolean)
    private boolean MakeFlag;
    @Field(type = FieldType.Boolean)
    private boolean FinishedGoodsFlag;
    @Field(type = FieldType.Text)
    private String Color;
    @Field(type = FieldType.Integer)
    private int SafetyStockLevel;
    @Field(type = FieldType.Integer)
    private int ReorderPoint;
    @Field(type = FieldType.Double)
    private double StandardCost;
    @Field(type = FieldType.Double)
    private double ListPrice;
    @Field(type = FieldType.Text)
    private String Size;
    @Field(type = FieldType.Text)
    private String SizeUnitMeasureCode;
    @Field(type = FieldType.Text)
    private String WeightUnitMeasureCode;
    @Field(type = FieldType.Double)
    private double Weight;
    @Field(type = FieldType.Integer)
    private int DaysToManufacture;
    @Field(type = FieldType.Text)
    private String ProductLine;
    @Field(type = FieldType.Text)
    private String Style;
    @Field(type = FieldType.Integer)
    private int ProductSubcategoryID;
    @Field(type = FieldType.Integer)
    private int ProductModelID;
    @Field(type = FieldType.Text)
    private String Rowguid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime SellStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime SellEndDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime DiscontinuedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime ModifiedDate;


}