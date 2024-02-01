package hh.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Double)
    private String price;

    @Field(type = FieldType.Text)
    private String des;


//    @Field(type = FieldType.Integer)
//    private int ProductID;
//
//    @Field(type = FieldType.Text)
//    private String Name;
//
//    @Field(type = FieldType.Text)
//    private String ProductNumber;
//
//    @Field(type = FieldType.Boolean)
//    private boolean MakeFlag;
//
//    @Field(type = FieldType.Boolean)
//    private boolean FinishedGoodsFlag;
//
//    @Field(type = FieldType.Text)
//    private String Color;
//
//    @Field(type = FieldType.Integer)
//    private int SafetyStockLevel;
//
//    @Field(type = FieldType.Integer)
//    private int ReorderPoint;
//
//    @Field(type = FieldType.Double)
//    private double StandardCost;
//
//    @Field(type = FieldType.Double)
//    private double ListPrice;
//
//    @Field(type = FieldType.Text)
//    private String Size;
//
//    @Field(type = FieldType.Text)
//    private String SizeUnitMeasureCode;
//
//    @Field(type = FieldType.Text)
//    private String WeightUnitMeasureCode;
//
//    @Field(type = FieldType.Double)
//    private double Weight;
//
//    @Field(type = FieldType.Integer)
//    private int DaysToManufacture;
//
//    @Field(type = FieldType.Text)
//    private String ProductLine;
//
//    @Field(type = FieldType.Text)
//    private String Style;
//
//    @Field(type = FieldType.Integer)
//    private int ProductSubcategoryID;
//
//    @Field(type = FieldType.Integer)
//    private int ProductModelID;
//
//    @Field(type = FieldType.Text)
//    private String Rowguid;
//
//    @Field(type = FieldType.Text)
//    private String SellStartDate;
//
//    @Field(type = FieldType.Text)
//    private String SellEndDate;
//
//    @Field(type = FieldType.Text)
//    private String DiscontinuedDate;
//
//    @Field(type = FieldType.Text)
//    private String ModifiedDate;


}