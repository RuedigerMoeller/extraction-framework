package org.dbpedia.extraction.sources

import org.dbpedia.extraction.wikiparser.WikiTitle
import org.dbpedia.extraction.util.StringUtils._

/**
 * Created by IntelliJ IDEA.
 * User: Mohamed Morsey
 * Date: 9/14/11
 * Time: 1:22 PM
 *
 * Represents a live wiki page.
 * 
 * TODO: remove this class, add fields for contributor to base class.
 *
 * @param title The title of this page
 * @param id The page ID
 * @param revision The revision of this page
 * @param source The WikiText source of this page
 */

class LiveWikiPage(title : WikiTitle, id : Long, revision : Long,
                        source : String, revisionTimestamp: String, val contributorID: Long,
                        val contributorName: String)
  extends WikiPage(title, null, id, revision, parseTimestamp(revisionTimestamp), source)
  //extends WikiPage(title, id, revision, source)
//WikiPage(WikiTitle, WikiTitle, Long, Long,Long, String)
{
  override def toString = "LiveWikiPage(" + title + "," + id + "," + revision + "," + "," +
    timestamp + "," + source + ")"

  /**
   * Serializes this page to XML using the MediaWiki export format.
   * The MediaWiki export format is specified at http://www.mediawiki.org/xml/export-0.4.
   */
  // TODO: see WikiPage.toDumpXML(): use formatInt, formatLong, formatTimestamp
  def toXML =
  {
    <mediawiki xmlns="http://www.mediawiki.org/xml/export-0.4/"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://www.mediawiki.org/xml/export-0.4/ http://www.mediawiki.org/xml/export-0.4.xsd"
               version="0.4"
               xml:lang="en">
      <page>
        <title>{title.decodedWithNamespace}</title>
        <id>{id}</id>
        <revision>
          <id>{revision}</id>
          <timestamp>{timestamp}</timestamp>
          <text xml:space="preserve">{source}</text>
        </revision>
      </page>
    </mediawiki>
  }

}
