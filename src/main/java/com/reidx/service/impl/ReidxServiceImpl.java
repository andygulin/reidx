package com.reidx.service.impl;

import com.reidx.entity.ReidxLog;
import com.reidx.entity.RelatedInfo;
import com.reidx.entity.Repeat;
import com.reidx.entity.Resource;
import com.reidx.repository.ReidxLogRepository;
import com.reidx.repository.ResourceRepository;
import com.reidx.service.ReidxService;
import com.reidx.util.Configuration;
import com.reidx.util.HtmlUtils;
import com.reidx.vo.ReidxParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ReidxServiceImpl implements ReidxService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ReidxLogRepository reidxLogRepository;
    @Autowired
    private Configuration configuration;

    @Override
    public List<Resource> reidx(ReidxParam param) {
        List<Resource> resources = new ArrayList<>();
        String requestUrl = this.configuration.getRedixGetUrl() + "?appkey=%s&kid=%s&id=%s&num=%s&type=%s";
        requestUrl = String.format(requestUrl, this.configuration.getAppKey(), this.configuration.getTheme(),
                param.getReidxId(), param.getNum(), param.getType());
        Document doc = getDocument(requestUrl);
        @SuppressWarnings("unchecked")
        List<Node> nodes = doc.selectNodes("//resource");
        for (Node node : nodes) {
            Resource resource = new Resource();
            resource.setReidxId(checkNull(node, "id"));
            resource.setUrl(checkNull(node, "url"));
            resource.setReferUrl(checkNull(node, "refer_url"));
            resource.setVer(NumberUtils.toInt(checkNull(node, "ver")));
            resource.setRelatedFields(NumberUtils.toInt(checkNull(node, "related_fields")));
            resource.setKeywordsCode(checkNull(node, "keywords_code"));
            String title = checkNull(node, "title");
            if (StringUtils.isBlank(title) || StringUtils.isEmpty(title)) {
                continue;
            } else {
                title = HtmlUtils.html2Text(title);
            }
            resource.setTitle(title);
            resource.setFormatContent(checkNull(node, "format_content"));
            resource.setAuthor(checkNull(node, "author"));
            resource.setContentMediaName(checkNull(node, "content_media_name"));
            resource.setWords(NumberUtils.toInt(checkNull(node, "words")));
            resource.setReleaseDate(convertDate(checkNull(node, "release_date")));
            resource.setAddTime(convertDate(checkNull(node, "add_time")));
            resource.setNavigation(checkNull(node, "navigation"));
            resource.setMediaId(checkNull(node, "media_id"));
            resource.setMediaName(checkNull(node, "media_name"));
            resource.setMediaUrl(checkNull(node, "media_url"));
            resource.setSourceType(NumberUtils.toInt(checkNull(node, "source_type")));
            resource.set_abstract(checkNull(node, "abstract"));
            resource.setKeywords(checkNull(node, "keywords"));
            resource.setRelativity(NumberUtils.toInt(checkNull(node, "relativity")));
            resource.setRelavancy(NumberUtils.toInt(checkNull(node, "relavancy")));
            resource.setRepeatCount(NumberUtils.toInt(checkNull(node, "repeat_count")));
            resource.setCommentCount(NumberUtils.toInt(checkNull(node, "comment_count")));
            resource.setClickCount(NumberUtils.toInt(checkNull(node, "click_count")));
            resource.setQuoteCount(NumberUtils.toInt(checkNull(node, "quote_count")));
            resource.setUrlCrc(checkNull(node, "url_crc"));
            resource.setTitleCrc(checkNull(node, "title_crc"));
            resource.setContentCrc(checkNull(node, "content_crc"));
            resource.setRelatedInfos(getRelatedInfo(node));
            resource.setPictureList(getPictuteList(node, "picture_list"));
            resource.setRepeats(getRepeat(node));
            resources.add(resource);
        }

        this.resourceRepository.save(resources);
        ReidxLog reidxLog = new ReidxLog();
        reidxLog.setLogDate(new Date());
        reidxLog.setReidxParam(param);
        this.reidxLogRepository.save(reidxLog);
        String next_id = doc.selectSingleNode("//next_id").getText();
        this.writeNextIdFile(next_id);
        return resources;
    }

    private Document getDocument(String requestUrl) {
        SAXReader reader = new SAXReader(false);
        reader.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));
        Document doc = null;
        try {
            doc = reader.read(new URL(requestUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private Date convertDate(String date_str) {
        try {
            return DateUtils.parseDate(date_str, new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException e) {
            return null;
        }
    }

    private String checkNull(Node node, String key) {
        return node.selectSingleNode(key) != null ? node.selectSingleNode(key).getText() : StringUtils.EMPTY;
    }

    private List<String> getPictuteList(Node node, String key) {
        String[] pl = checkNull(node, key).split("\n|;");
        return Arrays.asList(pl);
    }

    private List<Repeat> getRepeat(Node node) {
        @SuppressWarnings("unchecked")
        List<Node> nodes = node.selectNodes("repeat/resource");
        List<Repeat> repeats;
        if (CollectionUtils.isNotEmpty(nodes)) {
            repeats = new ArrayList<>(nodes.size());
            for (Node _node : nodes) {
                Repeat repeat = new Repeat();
                repeat.setReidxId(checkNull(_node, "id"));
                repeat.setUrl(checkNull(_node, "url"));
                repeat.setReferUrl(checkNull(_node, "refer_url"));
                repeat.setAuthor(checkNull(_node, "author"));
                repeat.setReleaseDate(convertDate(checkNull(_node, "release_date")));
                repeat.setAddTime(convertDate(checkNull(_node, "add_time")));
                repeat.setMediaId(checkNull(_node, "media_id"));
                repeat.setMediaName(checkNull(_node, "media_name"));
                repeat.setMediaUrl(checkNull(_node, "media_url"));
                repeat.setTitleCrc(checkNull(_node, "title_crc"));
                repeat.setUrlCrc(checkNull(_node, "url_crc"));
                repeat.setContentCrc(checkNull(_node, "content_crc"));

                repeats.add(repeat);
            }
            return repeats;
        }
        return new ArrayList<>();
    }

    private List<RelatedInfo> getRelatedInfo(Node node) {
        @SuppressWarnings("unchecked")
        List<Node> nodes = node.selectNodes("related_info/item");
        List<RelatedInfo> clues;
        if (CollectionUtils.isNotEmpty(nodes)) {
            clues = new ArrayList<>(nodes.size());
            for (Node _node : nodes) {
                RelatedInfo relatedInfo = new RelatedInfo();
                relatedInfo.setClue(checkNull(_node, "clue"));
                relatedInfo.setTag(checkNull(_node, "tag"));
                clues.add(relatedInfo);
            }
            return clues;
        }
        return new ArrayList<>();
    }

    @Override
    public void writeNextIdFile(String next_id) {
        File file = new File(FileUtils.getUserDirectoryPath(), this.configuration.getNextIdFile());
        try {
            FileUtils.writeStringToFile(file, next_id, Charset.forName(this.configuration.getCharset()), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readNextIdFile() {
        File file = new File(FileUtils.getUserDirectoryPath(), this.configuration.getNextIdFile());
        String next_id = "";
        if (file.exists()) {
            try {
                next_id = FileUtils.readFileToString(file, Charset.forName(this.configuration.getCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return next_id;
    }

    @Override
    public String getInitNextId() {
        return DateFormatUtils.format(new Date(), "yyyy_MM_dd0");
    }
}
