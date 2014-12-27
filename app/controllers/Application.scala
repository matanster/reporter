package controllers

import models._
import play.api._
import play.api.mvc._
import play.api.db.slick._
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.meta._
import play.api.http.MimeTypes

trait Match {
  type Match = (String, String, String, String, String, String, Boolean, String)

  // slick class binding definition
  class Matches(tag: Tag) extends Table[Match](tag, "Matches") {
    def docName = column[String]("docName")
    def runID = column[String]("runID")
    def sentence = column[String]("sentence", O.Length(20000,varying=true), O.DBType("binary"))
    def matchPattern = column[String]("matchPattern")
    def locationActual = column[String]("locationActual")
    def locationTest = column[String]("locationTest")    
    def isFinalMatch = column[Boolean]("fullMatch")
    def matchIndication = column[String]("matchIndication")
    def * = (runID, docName, sentence, matchPattern, locationTest, locationActual, isFinalMatch, matchIndication)
  }
  
  // the table handle
  val matches = TableQuery[Matches]
}

object Application extends Controller with Match {

  def index = Action { implicit request =>
    Ok(s"app is up, got request [$request]")
  }

  def showExtract(article: String) = DBAction { implicit request =>
    val rows = matches.filter(_.runID === "ubuntu-2014-12-15 21:09:52.072").filter(_.docName === article).list
    //val content = rows.map(r => (r._3)
    val content: List[Match1] = rows.map(r => Match1(r._1, r._2, r._3, r._4, r._5, r._6, r._7, r._8))
    //Ok(s"sentences: ${sentences.length}")
    println(content)
    Ok(views.html.showExtract("articlio research")(content))
  }
  
  def showExtractFoundation(article: String) = DBAction { implicit request =>
    val rows = matches.filter(_.runID === "ubuntu-2014-12-15 21:09:52.072").filter(_.docName === article).list
    //val content = rows.map(r => (r._3)
    val content: List[Match1] = rows.map(r => Match1(r._1, r._2, r._3, r._4, r._5, r._6, r._7, r._8))
    //Ok(s"sentences: ${sentences.length}")
    println(content)
    Ok(views.html.showExtractFoundation("articlio research")(content))
  }
  
}