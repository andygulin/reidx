package com.reidx.service;

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
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.reidx.entity.ReidxLog;
import com.reidx.entity.RelatedInfo;
import com.reidx.entity.Repeat;
import com.reidx.entity.Resource;
import com.reidx.repository.ReidxLogRepository;
import com.reidx.repository.ResourceRepository;
import com.reidx.util.Configuration;
import com.reidx.util.HtmlUtils;
import com.reidx.vo.ReidxParam;

@Service
public class ReidxService {

	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private ReidxLogRepository reidxLogRepository;
	@Autowired
	private Configuration configuration;

	public List<Resource> reidx(ReidxParam param) {
		List<Resource> resources = new ArrayList<>();
		String requestUrl = this.configuration.getRedixGetUrl() + "?appkey=%s&kid=%s&id=%s&num=%s&type=%s";
		requestUrl = String.format(requestUrl, this.configuration.getAppKey(), this.configuration.getTheme(),
				param.getReidxId(), param.getNum(), param.getType());
		SAXReader reader = new SAXReader(false);
		reader.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				return new InputSource(new StringReader(""));
			}
		});
		Document doc = null;
		try {
			doc = reader.read(new URL(requestUrl));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

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
			try {
				resource.setReleaseDate(
						DateUtils.parseDate(checkNull(node, "release_date"), new String[] { "yyyy-MM-dd HH:mm:ss" }));
				resource.setAddTime(
						DateUtils.parseDate(checkNull(node, "add_time"), new String[] { "yyyy-MM-dd HH:mm:ss" }));
			} catch (ParseException e) {
				e.printStackTrace();
			}
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

	private String checkNull(Node node, String key) {
		if (node.selectSingleNode(key) != null) {
			return node.selectSingleNode(key).getText();
		} else {
			return "";
		}
	}

	private List<String> getPictuteList(Node node, String key) {
		String[] pl = checkNull(node, key).split("\n|;");
		return Arrays.asList(pl);
	}

	private List<Repeat> getRepeat(Node node) {
		@SuppressWarnings("unchecked")
		List<Node> nodes = node.selectNodes("repeat/resource");
		List<Repeat> repeats = null;
		if (nodes != null && nodes.size() > 0) {
			repeats = new ArrayList<>(nodes.size());
			for (Node n : nodes) {
				Repeat repeat = new Repeat();
				repeat.setReidxId(checkNull(n, "id"));
				repeat.setUrl(checkNull(n, "url"));
				repeat.setReferUrl(checkNull(n, "refer_url"));
				repeat.setAuthor(checkNull(n, "author"));
				try {
					repeat.setReleaseDate(
							DateUtils.parseDate(checkNull(n, "release_date"), new String[] { "yyyy-MM-dd HH:mm:ss" }));
					repeat.setAddTime(
							DateUtils.parseDate(checkNull(n, "add_time"), new String[] { "yyyy-MM-dd HH:mm:ss" }));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				repeat.setMediaId(checkNull(n, "media_id"));
				repeat.setMediaName(checkNull(n, "media_name"));
				repeat.setMediaUrl(checkNull(n, "media_url"));
				repeat.setTitleCrc(checkNull(n, "title_crc"));
				repeat.setUrlCrc(checkNull(n, "url_crc"));
				repeat.setContentCrc(checkNull(n, "content_crc"));

				repeats.add(repeat);
			}
			return repeats;
		}
		return new ArrayList<Repeat>();
	}

	private List<RelatedInfo> getRelatedInfo(Node node) {
		@SuppressWarnings("unchecked")
		List<Node> nodes = node.selectNodes("related_info/item");
		List<RelatedInfo> clues = null;
		if (nodes != null && nodes.size() > 0) {
			clues = new ArrayList<>(nodes.size());
			for (Node n : nodes) {
				RelatedInfo relatedInfo = new RelatedInfo();
				relatedInfo.setClue(checkNull(n, "clue"));
				relatedInfo.setTag(checkNull(n, "tag"));
				clues.add(relatedInfo);
			}
			return clues;
		}
		return new ArrayList<RelatedInfo>();
	}

	public void writeNextIdFile(String next_id) {
		File file = new File(FileUtils.getUserDirectoryPath(), this.configuration.getNextIdFile());
		try {
			FileUtils.writeStringToFile(file, next_id, Charset.forName(this.configuration.getCharset()), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public String getInitNextId() {
		return DateFormatUtils.format(new Date(), "yyyy_MM_dd0");
	}
}
