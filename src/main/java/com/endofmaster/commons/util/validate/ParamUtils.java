package com.endofmaster.commons.util.validate;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class ParamUtils {

    /**
     * 从Map里查找参数，如果不存在抛MissingParamException
     *
     * @param map   参数表
     * @param param 参数名
     * @return 参数值
     */
    public static String findParam(Map<String, String> map, String param) {
        if (map != null && map.containsKey(param)) {
            return map.get(param);
        } else {
            throw new MissingParamException(param);
        }
    }

    /**
     * 从Map里查找参数，如果不存在返回null
     *
     * @param map   参数表
     * @param param 参数名
     * @return 参数值
     */
    public static String findParamNoEx(Map<String, String> map, String param) {
        if (map != null && map.containsKey(param)) {
            return map.get(param);
        } else {
            return null;
        }
    }

    /**
     * 从Map里查找参数并转换成想要的类型，如果不存在抛MissingParamException
     *
     * @param map      参数表
     * @param param    参数名
     * @param clazz<T> 参数类型
     * @return 参数值
     */
    public static <T> T findParam(Map<String, Object> map, String param, Class<T> clazz) {
        if (map != null && map.containsKey(param)) {
            return clazz.cast(map.get(param));
        } else {
            throw new MissingParamException(param);
        }
    }

    /**
     * 从指定Xml节点里查找参数，如果不存在抛MissingParamException
     *
     * @param element xml节点
     * @param param   参数名
     * @return 参数值
     */
    public static String findParam(Element element, String param) {
        String value = element.elementTextTrim(param);
        if (value != null) {
            return value;
        } else {
            throw new MissingParamException(param);
        }
    }

    /**
     * 从指定Json节点里查找参数，如果不存在抛MissingParamException
     *
     * @param node  JSON节点
     * @param param 参数名
     * @return 参数值
     */
    public static String findParam(JsonNode node, String param) {
        JsonNode subNode = node.get(param);
        if (subNode == null) {
            throw new MissingParamException(param);
        }
        if (!subNode.isValueNode()) {
            throw new InvalidParamException(param);
        }
        return subNode.asText();
    }

    /**
     * 判断json是否有该节点
     *
     * @param node  json节点
     * @param param 参数名
     * @return true为有
     */
    public static boolean containsParam(JsonNode node, String param) {
        JsonNode subNode = node.get(param);
        return subNode != null;
    }

    /**
     * 获取xml根节点内标签
     *
     * @param xml xml字符串
     * @return 参数键值对map
     * @throws DocumentException dom4j内部异常
     */
    public static Map<String, String> parseXml(String xml) throws DocumentException {
        Map<String, String> params = new HashMap<>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        for (Object node : root.elements()) {
            Element ele = (Element) node;
            params.put(ele.getName(), ele.getText());
        }
        return params;
    }

    /**
     * 将键值对类型的参数转换成xml
     *
     * @param params   参数键值对
     * @param rootName 根节点名
     * @return xml
     */
    public static String buildXml(Map<String, String> params, String rootName) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(rootName);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            root.addElement(entry.getKey()).addText(entry.getValue());
        }
        return document.asXML();
    }

    /**
     * 将k1=v1&k2=v2形式的字符串解析成Map
     *
     * @param kvString k1=v1&k2=v2形式的字符串
     * @return Map，如果content为null则返回null    value为null存""
     */
    public static Map<String, String> parseKvString(String kvString) {
        return parseKvString(kvString, '&');
    }

    /**
     * 将k1=v1,k2=v2形式的字符串解析成Map
     *
     * @param kvString k1=v1&k2=v2形式的字符串
     * @return Map，如果content为null则返回null    value为null存""
     */
    public static Map<String, String> parseKvStringByComma(String kvString) {
        return parseKvString(kvString, ',');
    }

    /**
     * 将k1=v1{symbol}k2=v2形式的字符串解析成Map
     *
     * @param kvString k1=v1{symbol}k2=v2形式的字符串
     * @param symbol   符号
     * @return Map，如果content为null则返回null    value为null存""
     */
    public static Map<String, String> parseKvString(String kvString, char symbol) {
        if (kvString == null)
            return null;
        Map<String, String> params = new HashMap<>();
        String[] keyValuePairs = StringUtils.split(kvString, symbol);
        for (String keyValuePair : keyValuePairs) {
            String[] fields = StringUtils.split(keyValuePair, '=');
            if (fields.length != 2) {
                params.put(fields[0], "");
            } else {
                params.put(fields[0], fields[1]);
            }
        }
        return params;
    }


    /**
     * 从json节点中查找参数如果无值返回null
     *
     * @param node
     * @param param
     * @return
     */
    public static String findParamReturnNull(JsonNode node, String param) {
        JsonNode subNode = node.get(param);
        if (subNode == null) {
            return null;
        }
        if (!subNode.isValueNode()) {
            return null;
        }
        return subNode.asText();
    }
}
