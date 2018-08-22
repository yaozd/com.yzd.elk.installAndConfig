package com.yzd.example.elasticsearch.demo.configES;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/***
 *
 *
 * Created by yzd on 2018/8/22 15:32.
 */

@Data
@NoArgsConstructor
@XmlRootElement(name="arrayOfTemplateInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateInfoXmlES {
    //
    private List<TemplateInfoES> templateInfo;
}
