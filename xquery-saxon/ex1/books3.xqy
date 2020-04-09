let $books := (doc("books.xml")/books/book)
return <table><tr><th>Title</th><th>Price</th></tr>
{
   for $x in $books   
   order by $x/price
   return <tr><td>{data($x/title)}</td><td>{data($x/price)}</td></tr>
}
</table>