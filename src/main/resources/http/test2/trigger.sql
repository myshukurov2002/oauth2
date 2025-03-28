create or replace function function_increase_view()
    returns trigger as
$$
begin

update articles
set views_count = views_count + 1
where NEW.article_id = id;

return new;
end;
$$ language plpgsql;

create or replace trigger trigger_increase_view
    AFTER INSERT
    on view
    FOR EACH ROW
    EXECUTE FUNCTION function_increase_view();

