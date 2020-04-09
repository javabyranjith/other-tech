xquery version "1.0";
declare namespace json="http://www.json.org";
declare option exist:serialize "method=json media-type=text/javascript";


let $books := (doc("books.xml")/books/book)
return <results>
{
   for $x in $books
   where $x/price>30
   order by $x/price
   return $x/title
}
</results>